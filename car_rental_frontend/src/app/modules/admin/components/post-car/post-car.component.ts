import { Component } from '@angular/core';
import { CommonComponents } from '../../../../CommonComponents';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { AdminService } from '../../service/admin.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { Router } from '@angular/router';


@Component({
  selector: 'app-post-car',
  standalone: true,
  imports: [CommonComponents, ReactiveFormsModule, NgFor, NgIf, CommonModule, FormsModule],
  templateUrl: './post-car.component.html',
  styleUrl: './post-car.component.scss'
})
export class PostCarComponent {

  isSpinning: boolean = false;
  selectedFile!: any | null;
  imagePreview!: string | ArrayBuffer | null;
  listofOption: Array<{label: string; value: string}> = [];
  listOfBrands = ["BMW", "KIA"];
  listOfType = ["petrol", "cng"];
  listOfTrasmission = ["Manual", "Automated"];
  listOfColor = ["Red", "White", "Silver"]

  postCarForm!: FormGroup;

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.previewImage();
  }

  constructor(private fb: FormBuilder, private adminService: AdminService,
    private message: NzMessageService, private route: Router
  ) {}

  ngOnInit() {
    this.postCarForm = this.fb.group({
      name: [null, Validators.required],
      brand: [null, Validators.required],
      type: [null, Validators.required],
      color: [null, Validators.required],
      transmission: [null, Validators.required],
      price: [null, Validators.required],
      description: [null, Validators.required],
      year: [null, Validators.required],
    })
  }

  postCar() {
    console.log(this.postCarForm.value);
    this.isSpinning = true;
    const formData: FormData = new FormData();
    formData.append('image', this.selectedFile);
    formData.append('brand', this.postCarForm.get('brand')?.value);
    formData.append('name', this.postCarForm.get('name')?.value);
    formData.append('type', this.postCarForm.get('type')?.value);
    formData.append('color', this.postCarForm.get('color')?.value);
    formData.append('transmission', this.postCarForm.get('transmission')?.value);
    formData.append('price', this.postCarForm.get('price')?.value);
    formData.append('description', this.postCarForm.get('description')?.value);
    formData.append('year', this.postCarForm.get('year')?.value);
    console.log(formData)
    
    this.adminService.postCar(formData).subscribe((res) => {
      this.isSpinning = false;
      this.message.success("Car Posted Successfully", {nzDuration: 5000});
      this.route.navigateByUrl("/admin/dashboard")
      console.log(res);
    }, error => {
      this.isSpinning = false;
      this.message.error("Error While posting car!!!", {nzDuration: 5000});
    })
  }

  previewImage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    }
    reader.readAsDataURL(this.selectedFile);
  }

}
