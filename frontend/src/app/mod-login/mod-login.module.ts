import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ModLoginRoutingModule} from './mod-login-routing.module';
import {ModLoginComponent} from './mod-login.component';
import {LoginComponent} from './login/login.component';
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    ModLoginComponent,
    LoginComponent
  ],
    imports: [
        CommonModule,
        ModLoginRoutingModule,
        ReactiveFormsModule
    ]
})
export class ModLoginModule { }
