import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ModResetPasswordComponent } from './mod-reset-password.component';
import {ResetPasswordComponent} from "./reset-password/reset-password.component";
import {NewPasswordComponent} from "./new-password/new-password.component";

const routes: Routes = [

  { path: '', component: ModResetPasswordComponent, children: [
    {path: '', component:ResetPasswordComponent, pathMatch: 'full' }
  ] },

  { path: 'newPassword/:token', component: NewPasswordComponent },

];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModResetPasswordRoutingModule { }
