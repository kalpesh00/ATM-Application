import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AtmDisplayComponent} from './atm-display/atm-display.component';
import { BalanceCheckComponent } from './balance-check/balance-check.component';
import { WithdrawalComponent } from './withdrawal/withdrawal.component';
const routes: Routes = [
  { path: '',redirectTo: '/atm', pathMatch: 'full' },
  { path: 'atm' ,component:AtmDisplayComponent},
  { path: 'atm/balanceCheck', component: BalanceCheckComponent},
  { path: 'atm/withdrawal', component: WithdrawalComponent}
 ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
