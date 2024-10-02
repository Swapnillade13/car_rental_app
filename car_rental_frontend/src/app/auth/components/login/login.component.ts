import { Component } from '@angular/core';


import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CommonComponents } from '../../../CommonComponents';
import { AuthService } from '../../service/auth/auth.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { StorageService } from '../../service/storage/storage.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterLink, ReactiveFormsModule, CommonComponents],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  isSpinning: boolean = false;
  loginForm!: FormGroup;

  constructor(private fb: FormBuilder, private auth: AuthService, private message: NzMessageService,  private route: Router) {}

  ngOnInit() {
    this.loginForm = this.fb.group({
      email: [null, [Validators.email, Validators.required]],
      password: [null, [Validators.required]]
    })
  }

  login() {
    this.auth.login(this.loginForm.value).subscribe((res) => {
      console.log(res);
      if(res.jwt != null) {
        this.message.success("Successfully LOGIN!!!", {nzDuration: 5000 });
        const user = {
          userId : res.userId,
          role : res.userRole
        }
        StorageService.saveToken(res.jwt);
        StorageService.saveUser(user);
        if(StorageService.isAdminLoggedIn()) {
          this.route.navigateByUrl("/admin/dashboard");
        } else if(StorageService.isCustomerLoggedIn()) {
          this.route.navigateByUrl("/customer/dashboard");
        } else {
          this.message.error("Bad Credential!!!", {nzDuration: 5000 });
        }
      } else {
        this.message.error("Something went wrong!!!", {nzDuration: 5000 });
      }
    })
  }
}
