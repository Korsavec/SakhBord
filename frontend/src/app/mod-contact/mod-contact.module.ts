import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ModContactRoutingModule} from './mod-contact-routing.module';
import {ModContactComponent} from './mod-contact.component';
import {ContactComponent} from './contact/contact.component';


@NgModule({
  declarations: [
    ModContactComponent,
    ContactComponent
  ],
  imports: [
    CommonModule,
    ModContactRoutingModule
  ]
})
export class ModContactModule { }
