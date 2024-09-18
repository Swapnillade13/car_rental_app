import { Component } from '@angular/core';

import { NgIf } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormControl } from '@angular/forms';
import { AuthService } from '../../service/auth/auth.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { Router, RouterLink } from '@angular/router';
import { CommonComponents } from '../../../CommonComponents';

@Component({
  selector: 'app-singup',
  standalone: true,
  imports: [CommonComponents, ReactiveFormsModule, NgIf, RouterLink],
  templateUrl: './singup.component.html',
  styleUrl: './singup.component.scss'
})
export class SingupComponent {

  isSpinning: boolean = false; 
  singupForm!: FormGroup;
 
  constructor(private fb: FormBuilder, private auth: AuthService, private message: NzMessageService, private route: Router) {}

  ngOnInit() {
    this.singupForm = this.fb.group({
      name: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required]],
      checkPassword: [null, [Validators.required, this.confirmValidation]]
    })
  }

  confirmValidation = (control : FormControl): {[s: string]: boolean} => {
    if(!control.value) {
      return {required: true}
    } else if (control.value !== this.singupForm.controls['password'].value) {
      return {confirm: true, error: true}
    }

    return {};
  } 

  register() {
    this.auth.register(this.singupForm.value).subscribe((res) => {
      if(res.id != null) {
        this.message.success("Successfully registered!!!", {nzDuration: 5000 });
        this.route.navigateByUrl("/login");
      } else {
        this.message.error("Something went wrong!!!", {nzDuration: 5000 });
      }
    })
  }
}
