import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../../auth/service/storage/storage.service';

const BASIC_URL = ["http://localhost:8080"]

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  postCar(carDao: any): Observable<any> {
    return this.http.post(BASIC_URL + "/api/admin/car", carDao, {
      headers: this.createAuthorizationHeader()
    });
  }

  getAllCars(): Observable<any> {
    return this.http.get(BASIC_URL+ "/api/admin/cars",{
      headers: this.createAuthorizationHeader()
    })
  }

  deleteCar(id: number): Observable<any> {
    return this.http.delete(BASIC_URL+ "/api/admin/car/" +id,{
      headers: this.createAuthorizationHeader()
    })
  }
 
  getCarById(id: number): Observable<any>{
    return this.http.get(BASIC_URL+ "/api/admin/car/" +id,{
      headers: this.createAuthorizationHeader()
    })
  }

  updateCar(id: number, carDto: any): Observable<any> {
    return this.http.put(BASIC_URL+ "/api/admin/car/"+id, carDto, {
      headers: this.createAuthorizationHeader()
    })
  }

  createAuthorizationHeader(): HttpHeaders {
    let authHeader: HttpHeaders = new HttpHeaders();
    return authHeader.set(
      'Authorization',
      'Bearer ' + StorageService.getToken()
    )
  }

}
