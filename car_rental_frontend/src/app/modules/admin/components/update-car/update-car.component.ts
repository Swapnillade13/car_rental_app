import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { CommonComponents } from '../../../../CommonComponents';
import { CommonModule, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-update-car',
  standalone: true,
  imports: [CommonComponents, ReactiveFormsModule, NgFor, NgIf, CommonModule, FormsModule],
  templateUrl: './update-car.component.html',
  styleUrl: './update-car.component.scss'
})
export class UpdateCarComponent {
  existingImage: string | null = null;
  updateForm!: FormGroup;
  imageChanged: boolean = false;
  listofOption: Array<{label: string; value: string}> = [];
  listOfBrands = ["BMW", "KIA"];
  listOfType = ["petrol", "cng"];
  listOfTrasmission = ["Manual", "Automated"];
  listOfColor = ["Red", "White", "Silver"]
  isSpinning: boolean = false;
  selectedFile!: any | null;
  imagePreview!: string | ArrayBuffer | null;

  constructor(private adminService: AdminService,
    private activetedRoute: ActivatedRoute,private fb: FormBuilder, 
    private message: NzMessageService, private route: Router){}

  ngOnInit() {
    this.getCarById();
    this.updateForm = this.fb.group({
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

  getCarById() {
    let cardId = this.activetedRoute.snapshot.params["id"];
    this.adminService.getCarById(cardId).subscribe((res) => {
      const carDto = res;
      this.existingImage = 'data:image/jpeg;base64,'+ res.returnedImge
      this.updateForm.patchValue(carDto);
      console.log(this.existingImage)
    })
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.imageChanged = true
    this.existingImage = null;
    this.previewImage();
  }

  postCar() {
    console.log(this.updateForm.value);
    let cardId = this.activetedRoute.snapshot.params["id"];
    this.isSpinning = true;
    const formData: FormData = new FormData();
    if(this.imageChanged && this.selectedFile) {
      formData.append('image', this.selectedFile);
    }
    
    formData.append('brand', this.updateForm.get('brand')?.value);
    formData.append('name', this.updateForm.get('name')?.value);
    formData.append('type', this.updateForm.get('type')?.value);
    formData.append('color', this.updateForm.get('color')?.value);
    formData.append('transmission', this.updateForm.get('transmission')?.value);
    formData.append('price', this.updateForm.get('price')?.value);
    formData.append('description', this.updateForm.get('description')?.value);
    formData.append('year', this.updateForm.get('year')?.value);
    console.log(formData)
    
    this.adminService.updateCar(cardId, formData).subscribe((res) => {
      this.isSpinning = false;
      this.message.success("Car Updated Successfully", {nzDuration: 5000});
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
