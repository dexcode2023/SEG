import { Routes } from '@angular/router';
import { FormComponent } from './form/form.component';
import { UserDetailsComponent } from './user-details/user-details.component';

export const routes: Routes = [
  { path: '', component: FormComponent },
  { path: 'user-details', component: UserDetailsComponent }
];
