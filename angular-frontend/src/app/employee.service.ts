import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Employee } from "./employee";
import { HttpClient } from '@angular/common/http';
import { environment } from "src/environments/environment.development";


@Injectable({
    providedIn: 'root'
})
export class EmployeeService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient){ }

  public getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.apiServerUrl}/employee/all`);
  }

  public addEmployee(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(`${this.apiServerUrl}/employee/add`, employee);
  }

  public updateEmployee(employeeId: string, employee: Employee): Observable<Employee> {
    return this.http.put<Employee>(`${this.apiServerUrl}/employee/update/${employeeId}`, employee);
  }

  public deleteEmployee(employeeId: string): Observable<void> {
    return this.http.get<void>(`${this.apiServerUrl}/employee/remove/${employeeId}`);
  }
}