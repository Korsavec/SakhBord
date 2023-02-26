import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ModRulesRoutingModule} from './mod-rules-routing.module';
import {ModRulesComponent} from './mod-rules.component';
import {RulesComponent} from './rules/rules.component';


@NgModule({
  declarations: [
    ModRulesComponent,
    RulesComponent
  ],
  imports: [
    CommonModule,
    ModRulesRoutingModule
  ]
})
export class ModRulesModule { }
