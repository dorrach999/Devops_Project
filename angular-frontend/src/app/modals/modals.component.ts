import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-modals',
  templateUrl: './modals.component.html',
  styleUrls: ['./modals.component.scss']
})
export class ModalsComponent {
  employee: Employee = new Employee();

  constructor(public dialogRef: MatDialogRef<ModalsComponent>, private employeeService: EmployeeService) {}

  saveEmployee(): void {
    // Assuming employeeService is injected and available
    this.employeeService.addEmployee(this.employee).subscribe(
      (data: Employee) => {
        console.log(data);
        this.dialogRef.close(); // Close the modal on successful save
      },
      (error) => console.log(error)
    );
  }

  close(): void {
    this.dialogRef.close(); // Close the modal without saving
  }

  onSubmit(){
    console.log(this.employee);
    this.saveEmployee();
  }
}


