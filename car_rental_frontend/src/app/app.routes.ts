import { Routes } from '@angular/router';
import { SingupComponent } from './auth/components/singup/singup.component';
import { LoginComponent } from './auth/components/login/login.component';
import { AdminDashboardComponent } from './modules/admin/components/admin-dashboard/admin-dashboard.component';
import { CustomerDashboardComponent } from './modules/customer/components/customer-dashboard/customer-dashboard.component';

export const routes: Routes = [
    {path : "register", component: SingupComponent},
    {path : "login", component : LoginComponent},
    {path : "admin", component : AdminDashboardComponent},
    {path : "customer", component : CustomerDashboardComponent}
];
