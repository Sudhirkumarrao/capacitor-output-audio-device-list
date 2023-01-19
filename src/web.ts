import { WebPlugin } from '@capacitor/core';

import type { CapacitorAudioOutputListPlugin } from './definitions';

export class CapacitorAudioOutputListWeb extends WebPlugin implements CapacitorAudioOutputListPlugin {
  
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

  async getAudioDevices(): Promise<any> {
    console.log('Cordova required');
    return null;
  }

  async changeOutputDevice(device_type: { device_type: any; }): Promise<any> {
    console.log('Cordova required');
    return device_type;
  }
}
