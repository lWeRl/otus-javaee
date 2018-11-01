import {Component, OnInit} from '@angular/core';
import {Employee} from '../models/employee';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-departments-dashbord',
  templateUrl: './departments-dashbord.component.html',
  styleUrls: ['./departments-dashbord.component.scss']
})
export class DepartmentsDashbordComponent implements OnInit {



  constructor(
    private http: HttpClient
  ) {
  }

  ngOnInit() {
  }


}
