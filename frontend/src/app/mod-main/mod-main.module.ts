import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ModMainRoutingModule} from './mod-main-routing.module';
import {ModMainComponent} from './mod-main.component';
import {MainComponent} from './main/main.component';


@NgModule({
  declarations: [
    ModMainComponent,
    MainComponent
  ],
  imports: [
    CommonModule,
    ModMainRoutingModule
  ]
})
export class ModMainModule { }
