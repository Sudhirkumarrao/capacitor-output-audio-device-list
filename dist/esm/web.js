import { WebPlugin } from '@capacitor/core';
export class CapacitorAudioOutputListWeb extends WebPlugin {
    async echo(options) {
        console.log('ECHO', options);
        return options;
    }
    async getAudioDevices() {
        console.log('Cordova required');
        return null;
    }
    async changeOutputDevice(device_type) {
        console.log('Cordova required');
        return device_type;
    }
}
//# sourceMappingURL=web.js.map