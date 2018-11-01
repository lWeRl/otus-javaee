import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {EmployeesDashbordComponent} from './employees-dashbord/employees-dashbord.component';
import {DepartmentsDashbordComponent} from './departments-dashbord/departments-dashbord.component';
import {PositionsDashbordComponent} from './positions-dashbord/positions-dashbord.component';

const routes: Routes = [
  {path: 'employees', component: EmployeesDashbordComponent},
  {path: 'departments', component: DepartmentsDashbordComponent},
  {path: 'positions', component: PositionsDashbordComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeesRoutingModule {
}
