import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-defaultlogin',
  standalone: true,
  imports: [],
  templateUrl: './defaultlogin.component.html',
  styleUrls: ['./defaultlogin.component.scss']
})
export class DefaultloginComponent {
  @Input() title: string = "";
  @Input() primaryBtnText: string = "";
  @Input() secodaryBtnText: string = "";
  @Output("submit") onSubmit = new EventEmitter();
  @Output("navigate") onNavigate = new EventEmitter();

  submit(){
    this.onSubmit.emit();
  }
  navigate(){
    this.onNavigate.emit();
  }
}
