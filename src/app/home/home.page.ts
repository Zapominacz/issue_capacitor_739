import { Component } from '@angular/core';
import { registerPlugin } from '@capacitor/core';

// eslint-disable-next-line @typescript-eslint/naming-convention
const PermissionCheckPlugin: any = registerPlugin('PermissionCheck');

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {

  constructor() {}

  public checkPermission() {
    PermissionCheckPlugin.callPermission();
  }

  public startIntent() {
    PermissionCheckPlugin.pickImage();
  }

}
