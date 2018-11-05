import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {FormBuilder, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form: FormGroup;

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    const builder = new FormBuilder();
    this.form = builder.group({
      login: '',
      password: ''
    });
  }

  send() {

    this.http.post('http://localhost:8080/api/login', this.form.value, { withCredentials: true }).subscribe();
  }

}
