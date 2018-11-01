import {Department} from './department';

export interface Employee {
  id?: number;
  firstName?: string;
  lastName?: string;
  middleName?: string;
  login?: string;
  password?: string;
  department?: Department;
  position?: Position;
}
