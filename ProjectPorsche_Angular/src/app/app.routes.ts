import { Routes } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { CarListComponent } from './car-list/car-list.component';

export const routes: Routes = [
  { path: '', component: CarListComponent },
  { path: 'register', component: RegisterComponent }
];
