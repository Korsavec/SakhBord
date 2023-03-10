import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ModDashboardRoutingModule} from './mod-dashboard-routing.module';
import {ModDashboardComponent} from './mod-dashboard.component';
import {DashboardComponent} from './dashboard/dashboard.component';


@NgModule({
  declarations: [
    ModDashboardComponent,
    DashboardComponent
  ],
  imports: [
    CommonModule,
    ModDashboardRoutingModule
  ]
})
export class ModDashboardModule { }
