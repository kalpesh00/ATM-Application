import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DataTableDirective } from 'angular-datatables';
import { Subject } from 'rxjs';
import { InformationService } from '../service/information.service';

@Component({
  selector: 'app-withdrawal',
  templateUrl: './withdrawal.component.html',
  styleUrls: ['./withdrawal.component.css']
})
export class WithdrawalComponent implements OnInit {

  @ViewChild(DataTableDirective)
  dtElement: DataTableDirective;
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();
  dtTrigger1: Subject<any> = new Subject();

  newWithdrawForm: FormGroup;
  infoPresent: boolean = false;
  errorPresent: boolean = false;
  errorMessage:string = "";
  balanceData: any = {};
  NoteTypeList: any = [];

  constructor(private formBuilder: FormBuilder, private infoServ: InformationService) {
    this.newWithdrawForm = this.formBuilder.group({
      accntNo: ['', Validators.required],
      pin: ['', Validators.required],
      amount: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
    }
  }

  ngAfterViewInit(): void {
    this.dtTrigger.next();
    this.dtTrigger1.next();
  }

  rerender(): void {
    this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
      dtInstance.destroy();
      this.dtTrigger.next();
      this.dtTrigger1.next();
    });
  }

  onSubmit() {
  }

  resetForm() {
    this.newWithdrawForm.reset();
    this.infoPresent = false;
    this.errorPresent = false;
    this.errorMessage = "";
  }

  withdrawAmount() {
    if (this.newWithdrawForm.valid) {
      let data = this.newWithdrawForm.value;
      let input = {
        "accountNo": data.accntNo,
        "pin": data.pin
      }

      let amount = data.amount;

      this.infoServ.withdrawAmount(input, amount).subscribe(resp => {
        console.log(resp);
        this.balanceData = resp;
        if (this.balanceData.accountNo) {
          this.infoPresent = true;
          this.errorPresent = false;

          this.NoteTypeList = this.balanceData.notesList;

          this.rerender();
        }
      },
      errorCode => {
        console.log("Error in withdraw amount" + JSON.stringify(errorCode));
        this.errorMessage = errorCode.error.message;
        this.errorPresent = true;
        this.infoPresent = false;
      });

    }
  }

}
