import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {EmployeesRoutingModule} from './employees-routing.module';
import {EmployeeComponent} from './employee/employee.component';
import {PositionComponent} from './position/position.component';
import {DepartmentComponent} from './department/department.component';
import {EmployeesDashbordComponent} from './employees-dashbord/employees-dashbord.component';
import {DepartmentsDashbordComponent} from './departments-dashbord/departments-dashbord.component';
import {PositionsDashbordComponent} from './positions-dashbord/positions-dashbord.component';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  imports: [
    CommonModule,
    EmployeesRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  declarations: [
    EmployeeComponent,
    PositionComponent,
    DepartmentComponent,
    EmployeesDashbordComponent,
    DepartmentsDashbordComponent,
    PositionsDashbordComponent
  ],
  exports: [
    EmployeeComponent,
    PositionComponent,
    DepartmentComponent,
    EmployeesDashbordComponent
  ]
})
export class EmployeesModule {
}
