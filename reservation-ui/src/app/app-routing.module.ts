import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ReserveclubComponent } from './reserveclub/reserveclub.component';



const routes: Routes = [
  {path: '', redirectTo: 'reserveclub', pathMatch: 'full'},
  {path: 'reserveclub', component: ReserveclubComponent}
  /* ,
  {path: 'club', component: ClubComponent},
  
  {path: 'benefit', component: BenefitComponent},
  {path: 'benefitspkg', component: BenefitspkgComponent},
  {path: 'provider', component: ProvidergroupComponent},
  {path: 'rules', component: RulesComponent} */
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
