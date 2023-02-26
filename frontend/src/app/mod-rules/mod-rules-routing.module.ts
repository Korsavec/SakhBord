import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ModRulesComponent} from './mod-rules.component';
import {RulesComponent} from "./rules/rules.component";

const routes: Routes = [
  { path: '', component: ModRulesComponent, children: [
      {path: '', component: RulesComponent, pathMatch: 'full'}
    ] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModRulesRoutingModule { }
