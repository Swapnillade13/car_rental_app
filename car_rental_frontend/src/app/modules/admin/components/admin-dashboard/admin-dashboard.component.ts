import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { CommonComponents } from '../../../../CommonComponents';
import { CommonModule, NgFor } from '@angular/common';
import { NzMessageModule, NzMessageService } from 'ng-zorro-antd/message';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonComponents, NgFor, CommonModule, RouterLink],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.scss'
})
export class AdminDashboardComponent {

  cars: any = [];

  constructor(private adminService: AdminService, private message: NzMessageService) { }

  ngOnInit() {

    this.getAllCars();
  }

  getAllCars() {
    this.cars = [];
    return this.adminService.getAllCars().subscribe((res) => {
      console.log(res);
      res.forEach((element: { processedImg: string; returnedImge: string; }) => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImge
        this.cars.push(element);
      });
    })
  }

  deleteCar(id: number) {
    this.adminService.deleteCar(id).subscribe((res) => {
      console.log(res);
      this.getAllCars();
      this.message.success("Successfully deleted", { nzDuration: 5000 });
    })
  }

}
