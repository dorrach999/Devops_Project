import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit  } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { NgForm } from '@angular/forms';
import { Employee } from '../employee';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { ModalsComponent } from '../modals/modals.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-formpage',
  templateUrl: './formpage.component.html',
  styleUrls: ['./formpage.component.scss']
})
export class FormpageComponent  {
   employee: Employee = new Employee();
   constructor(private employeeService: EmployeeService,
    private router: Router) { }

  ngOnInit() {
   
  }
  saveEmployee(){
    this.employeeService.addEmployee(this.employee).subscribe( 
      (data: Employee) =>{
      console.log(data);
      this.goToEmployeeList();
    },
    error => console.log(error));
  }

  goToEmployeeList(){
    this.router.navigate(['/employees']);
  }
  
  onSubmit(){
    console.log(this.employee);
    this.saveEmployee();
  }
  

}
