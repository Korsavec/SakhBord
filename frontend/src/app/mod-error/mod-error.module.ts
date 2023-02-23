import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ModErrorRoutingModule} from './mod-error-routing.module';
import {ModErrorComponent} from './mod-error.component';


@NgModule({
  declarations: [
    ModErrorComponent
  ],
  imports: [
    CommonModule,
    ModErrorRoutingModule
  ]
})
export class ModErrorModule { }
