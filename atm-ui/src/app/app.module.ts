import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AtmDisplayComponent } from './atm-display/atm-display.component';
import { BalanceCheckComponent } from './balance-check/balance-check.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { DataTablesModule } from 'angular-datatables';
import { WithdrawalComponent } from './withdrawal/withdrawal.component';

@NgModule({
  declarations: [
    AppComponent,
    AtmDisplayComponent,
    BalanceCheckComponent,
    WithdrawalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    DataTablesModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
