import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ModResetPasswordRoutingModule} from './mod-reset-password-routing.module';
import {ModResetPasswordComponent} from './mod-reset-password.component';
import {ResetPasswordComponent} from './reset-password/reset-password.component';
import {ReactiveFormsModule} from "@angular/forms";
import {NewPasswordComponent} from './new-password/new-password.component';


@NgModule({
  declarations: [
    ModResetPasswordComponent,
    ResetPasswordComponent,
    NewPasswordComponent
  ],
    imports: [
        CommonModule,
        ModResetPasswordRoutingModule,
        ReactiveFormsModule
    ]
})
export class ModResetPasswordModule { }
