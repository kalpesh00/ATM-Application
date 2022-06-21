import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DataTableDirective, DataTablesModule } from 'angular-datatables';
import { Subject } from 'rxjs';
import { InformationService } from '../service/information.service';

@Component({
  selector: 'app-balance-check',
  templateUrl: './balance-check.component.html',
  styleUrls: ['./balance-check.component.css']
})
export class BalanceCheckComponent implements OnInit {

  @ViewChild(DataTableDirective)
  dtElement: DataTableDirective;
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();

  newBalCheckForm: FormGroup;
  infoPresent: boolean = false;
  balanceData: any = {};
  errorMessage:string = "";
  errorPresent: boolean = false;

  constructor(private formBuilder: FormBuilder, private infoServ: InformationService) {
    this.newBalCheckForm = this.formBuilder.group({
      accntNo: ['', Validators.required],
      pin: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
    }
  }

  ngAfterViewInit(): void {
    this.dtTrigger.next();
  }

  rerender(): void {
    this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
      dtInstance.destroy();
      this.dtTrigger.next();
    });
  }

  onSubmit() {
  }

  getBalance() {
    if (this.newBalCheckForm.valid) {
      let data = this.newBalCheckForm.value;
      let input = {
        "accountNo": data.accntNo,
        "pin": data.pin
      }

      this.infoServ.getBalance(input).subscribe(resp => {
        console.log(resp);
        this.balanceData = resp;
        
        if (this.balanceData.accountNo) {
          this.infoPresent = true;
          this.errorPresent = false;
          this.rerender();
        }
        
      },
        errorCode => {
          console.log("Error in getting balance" + JSON.stringify(errorCode));
         this.errorMessage = errorCode.error.message;
          // console.log(this.errorMessage);
          this.errorPresent = true;
          this.infoPresent = false;
        }
        );

      this.newBalCheckForm.reset();

    }
  }

  resetForm() {
    this.newBalCheckForm.reset();
    this.infoPresent = false;
    this.errorPresent = false;
    this.errorMessage = "";
  }

}
