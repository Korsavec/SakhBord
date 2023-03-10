import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ModMainRoutingModule} from './mod-main-routing.module';
import {ModMainComponent} from './mod-main.component';
import {MainComponent} from './main/main.component';
import {ReactiveFormsModule} from "@angular/forms";
import {FilterAdComponent} from './filter-ad/filter-ad.component';


@NgModule({
  declarations: [
    ModMainComponent,
    MainComponent,
    FilterAdComponent
  ],
    imports: [
        CommonModule,
        ModMainRoutingModule,
        ReactiveFormsModule
    ]
})
export class ModMainModule { }
