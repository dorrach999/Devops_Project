import { Component, ElementRef, OnInit, Optional, ViewChild } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { ModalsComponent } from '../modals/modals.component';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.scss']
})
export class EmployeesComponent  implements OnInit{
  public employees: Employee[] = [];
  // public editEmployee: Employee | null | undefined;
  public deleteEmployee!: Employee;

  constructor(@Optional() private dialog: MatDialog ,private employeeService: EmployeeService 
    
    ){}

  ngOnInit() {
    this.getEmployees();
  }

  public getEmployees(): void {
    this.employeeService.getEmployees().subscribe(
      (response: Employee[]) => {
        this.employees = response;
        console.log(this.employees);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


  public onDeleteEmployee(employeeId: string): void {
    this.employeeService.deleteEmployee(employeeId).subscribe(
      (response: void) => {
        console.log(response);
        this.getEmployees();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  openFormModal(): void {
    // const dialogConfig = new MatDialogConfig();
    // dialogConfig.position = {
    //   top: '10%',
    //   left: '20%',
    // };
    // //dialogConfig.panelClass = 'custom-modal'; // Add a custom class for styling
    // dialogConfig.autoFocus = true;
    const dialogRef = this.dialog.open(ModalsComponent ,{
      width:'500px',
    });
  
    dialogRef.afterClosed().subscribe(result => {
      console.log('The modal was closed');
      // You can add any logic here that should run after the modal is closed
    });
  }
  

  

  // openModal() {
  //   this.modalRef = this.modalService.open(ModalsComponent)
  // }



}
