package io.ionic.starter;

import android.Manifest;
import android.content.Intent;
import android.util.Log;

import androidx.activity.result.ActivityResult;

import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.ActivityCallback;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;

@CapacitorPlugin(name = "PermissionCheck", permissions = {
  @Permission(
    alias = "camera",
    strings = {Manifest.permission.CAMERA}
  )
,})
public class PermissionCheckPlugin extends Plugin {

  @PluginMethod()
  public void callPermission(PluginCall call) {
    if (getPermissionState("camera") != PermissionState.GRANTED) {
      requestPermissionForAlias("camera", call, "cameraPermsCallback");
    } else {
      Log.e("TEST", "Can now use camera!");
    }
  }

  @PermissionCallback
  private void cameraPermsCallback(PluginCall call) {
    if (getPermissionState("camera") == PermissionState.GRANTED) {
      Log.e("TEST", "Can now use camera (after long battle)!");
    } else {
      call.reject("Permission is required to take a picture");
    }
  }

  @PluginMethod()
  public void pickImage(PluginCall call) {
    Intent intent = new Intent(Intent.ACTION_PICK);
    intent.setType("image/*");

    // Start the Activity for result using the name of the callback method
    startActivityForResult(call, intent, "pickImageResult");
  }

  @ActivityCallback
  private void pickImageResult(PluginCall call, ActivityResult result) {
    if (call == null) {
      return;
    }
    Log.e("TEST", "Got image!" + result.getData().toString());
    // Do something with the result data
  }

}
