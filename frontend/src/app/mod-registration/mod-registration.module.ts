import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ModRegistrationRoutingModule} from './mod-registration-routing.module';
import {ModRegistrationComponent} from './mod-registration.component';
import {RegistrationComponent} from './registration/registration.component';
import {ReactiveFormsModule} from "@angular/forms";
import {ConfirmEmailComponent} from './confirm-email/confirm-email.component';


@NgModule({
  declarations: [
    ModRegistrationComponent,
    RegistrationComponent,
    ConfirmEmailComponent
  ],
    imports: [
        CommonModule,
        ModRegistrationRoutingModule,
        ReactiveFormsModule
    ]
})
export class ModRegistrationModule { }
