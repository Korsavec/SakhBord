import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ModDashboardComponent} from './mod-dashboard.component';
import {DashboardComponent} from "./dashboard/dashboard.component";

const routes: Routes = [

  { path: '', component: ModDashboardComponent, children: [
      {path: '', component: DashboardComponent, pathMatch: 'full'}
    ] }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModDashboardRoutingModule { }
