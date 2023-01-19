package aieze.link.vc.custom.plugin;

import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Build;
import android.widget.Toast;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

@CapacitorPlugin(name = "CapacitorAudioOutputList", requestCodes = { CapacitorAudioOutputListPlugin.AUDIO_OUTPUT_DEVICES })
public class CapacitorAudioOutputListPlugin extends Plugin {
    protected static final int AUDIO_OUTPUT_DEVICES = 2;
    AudioManager audioManager = null;
    private CapacitorAudioOutputList implementation = new CapacitorAudioOutputList();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }


    @PluginMethod()
    public void getAudioDevices(PluginCall call) throws JSONException {
      audioManager = (AudioManager) getActivity().getSystemService(getContext().AUDIO_SERVICE);
      List<JSObject> jsonArray = new ArrayList<>();

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        // AudioManager audioManager = (AudioManager) getActivity().getSystemService(getContext().AUDIO_SERVICE);
        List<AudioDeviceInfo> devices = audioManager.getAvailableCommunicationDevices();
        AudioDeviceInfo device = audioManager.getCommunicationDevice();
        for (int i=0; i<devices.size(); i++) {
          JSObject json = new JSObject();
          if (devices.get(i).getType() == AudioDeviceInfo.TYPE_BLUETOOTH_SCO) {
            json.put("label", "Bluetooth");
            json.put("type", devices.get(i).getType());
            json.put("active", audioManager.isBluetoothScoOn());
          }

          if (devices.get(i).getType() == AudioDeviceInfo.TYPE_BUILTIN_SPEAKER) {
            json.put("label", "Speaker");
            json.put("type", devices.get(i).getType());
            json.put("active", audioManager.isSpeakerphoneOn());
          }

          if (devices.get(i).getType() == AudioDeviceInfo.TYPE_WIRED_HEADSET) {
            json.put("label", "Wired Headset");
            json.put("type", devices.get(i).getType());
            json.put("active", audioManager.isWiredHeadsetOn());
          }

          if (devices.get(i).getType() == AudioDeviceInfo.TYPE_BUILTIN_EARPIECE) {
            json.put("label", "Phone");
            json.put("type", devices.get(i).getType());
            json.put("active", !(audioManager.isBluetoothScoOn() || audioManager.isSpeakerphoneOn() || audioManager.isWiredHeadsetOn()));
          }

  //        if (device.getType() == devices.get(i).getType()) {
  //          json.put("active", true);
  //        }
          jsonArray.add(json);
        }
      } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        AudioDeviceInfo[] devices = audioManager.getDevices(AudioManager.GET_DEVICES_OUTPUTS);
        for (AudioDeviceInfo device : devices) {
          JSObject json = new JSObject();
          if (device.getType() == AudioDeviceInfo.TYPE_BLUETOOTH_SCO) {
            json.put("label", "Bluetooth");
            json.put("type", device.getType());
            json.put("active", audioManager.isBluetoothScoOn());
          }

          if (device.getType() == AudioDeviceInfo.TYPE_BUILTIN_SPEAKER) {
            json.put("label", "Speaker");
            json.put("type", device.getType());
            json.put("active", audioManager.isSpeakerphoneOn());
          }

          if (device.getType() == AudioDeviceInfo.TYPE_WIRED_HEADSET) {
            json.put("label", "Wired Headset");
            json.put("type", device.getType());
            json.put("active", audioManager.isWiredHeadsetOn());
          }

          if (device.getType() == AudioDeviceInfo.TYPE_BUILTIN_EARPIECE) {
            json.put("label", "Phone");
            json.put("type", device.getType());
            json.put("active", !(audioManager.isBluetoothScoOn() || audioManager.isSpeakerphoneOn() || audioManager.isWiredHeadsetOn()));
          }
          jsonArray.add(json);
        }
      }

      Integer activeDevice = AudioDeviceInfo.TYPE_BUILTIN_SPEAKER;

      if (audioManager.isBluetoothScoOn()) {
        activeDevice = AudioDeviceInfo.TYPE_BLUETOOTH_SCO;
      } else if (audioManager.isWiredHeadsetOn()) {
        activeDevice = AudioDeviceInfo.TYPE_WIRED_HEADSET;
      } else if (audioManager.isSpeakerphoneOn()) {
        activeDevice = AudioDeviceInfo.TYPE_BUILTIN_SPEAKER;
      } else {
        activeDevice = AudioDeviceInfo.TYPE_BUILTIN_EARPIECE;
      }

      JSObject finalJson = new JSObject();
      finalJson.put("result", jsonArray);
      finalJson.put("active-device", activeDevice);
      call.resolve(finalJson);
    }

    @PluginMethod()
    public void changeOutputDevice(PluginCall call) throws JSONException {
  //    AudioManager audioManager = (AudioManager) getActivity().getSystemService(getContext().AUDIO_SERVICE);
      Integer selectedDevice = call.getInt("device_type", 2);
      AudioDeviceInfo selectedAudioDevice = null;

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        List<AudioDeviceInfo> devices = audioManager.getAvailableCommunicationDevices();
        for (int i=0; i<devices.size(); i++) {
          if (selectedDevice == devices.get(i).getType()) {
            selectedAudioDevice = devices.get(i);
            break;
          }
        }

        if (selectedAudioDevice.getType() == AudioDeviceInfo.TYPE_BLUETOOTH_SCO) {
          audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
          audioManager.startBluetoothSco();
          audioManager.setBluetoothScoOn(true);
          audioManager.setSpeakerphoneOn(false);
          audioManager.setWiredHeadsetOn(false);
        }else if (selectedAudioDevice.getType() == AudioDeviceInfo.TYPE_WIRED_HEADSET) {
          audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
          audioManager.stopBluetoothSco();
          audioManager.setBluetoothScoOn(false);
          audioManager.setSpeakerphoneOn(false);
          audioManager.setWiredHeadsetOn(true);
        } else if (selectedAudioDevice.getType() == AudioDeviceInfo.TYPE_BUILTIN_SPEAKER) {
          audioManager.setMode(AudioManager.MODE_NORMAL);
          audioManager.stopBluetoothSco();
          audioManager.setBluetoothScoOn(false);
          audioManager.setSpeakerphoneOn(true);
          audioManager.setWiredHeadsetOn(false);
        } else if (selectedAudioDevice.getType() == AudioDeviceInfo.TYPE_BUILTIN_EARPIECE) {
          audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
          audioManager.stopBluetoothSco();
          audioManager.setBluetoothScoOn(false);
          audioManager.setSpeakerphoneOn(false);
          audioManager.setWiredHeadsetOn(false);
        }


        if (selectedAudioDevice != null) {
          boolean result = audioManager.setCommunicationDevice(selectedAudioDevice);
          if (!result) {
            Toast.makeText(getContext(), "Errror Occurred", Toast.LENGTH_SHORT).show();
          }


        }
      } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        AudioDeviceInfo[] devices = audioManager.getDevices(AudioManager.GET_DEVICES_OUTPUTS);
        for(AudioDeviceInfo device : devices) {
          if (selectedDevice == device.getType()) {
            selectedAudioDevice = device;
            break;
          }
        }
      }
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (selectedAudioDevice.getType() == AudioDeviceInfo.TYPE_BLUETOOTH_SCO) {
          audioManager.setBluetoothScoOn(true);
          audioManager.startBluetoothSco();
          audioManager.setSpeakerphoneOn(false);
          audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        }else if (selectedAudioDevice.getType() == AudioDeviceInfo.TYPE_WIRED_HEADSET) {
          audioManager.stopBluetoothSco();
          audioManager.setBluetoothScoOn(false);
          audioManager.setSpeakerphoneOn(false);
          audioManager.setWiredHeadsetOn(true);
          audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        } else if (selectedAudioDevice.getType() == AudioDeviceInfo.TYPE_BUILTIN_SPEAKER) {
          audioManager.stopBluetoothSco();
          audioManager.setBluetoothScoOn(false);
          audioManager.setSpeakerphoneOn(true);
          audioManager.setMode(AudioManager.MODE_NORMAL);
        } else if (selectedAudioDevice.getType() == AudioDeviceInfo.TYPE_BUILTIN_EARPIECE) {
          audioManager.stopBluetoothSco();
          audioManager.setBluetoothScoOn(false);
          audioManager.setSpeakerphoneOn(false);
          audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        }
      }
      call.resolve();
    }
}
