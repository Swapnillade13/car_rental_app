import { NgModule } from "@angular/core";

import { NzSpinModule } from 'ng-zorro-antd/spin';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzDatePickerModule } from 'ng-zorro-antd/date-picker';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { CommonModule } from "@angular/common";




@NgModule({
    exports: [
        CommonModule, NzSpinModule, NzFormModule, NzButtonModule, NzInputModule, NzLayoutModule, NzDatePickerModule, NzSelectModule
    ]
  })
  export class CommonComponents {}