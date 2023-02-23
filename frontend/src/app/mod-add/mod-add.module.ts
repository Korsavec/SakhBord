import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ModAddRoutingModule} from './mod-add-routing.module';
import {ModAddComponent} from './mod-add.component';
import {AddComponent} from './add/add.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    ModAddComponent,
    AddComponent
  ],
    imports: [
        CommonModule,
        ModAddRoutingModule,
        FormsModule,
        ReactiveFormsModule
    ]
})
export class ModAddModule { }
