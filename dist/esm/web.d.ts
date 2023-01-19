import { WebPlugin } from '@capacitor/core';
import type { CapacitorAudioOutputListPlugin } from './definitions';
export declare class CapacitorAudioOutputListWeb extends WebPlugin implements CapacitorAudioOutputListPlugin {
    echo(options: {
        value: string;
    }): Promise<{
        value: string;
    }>;
    getAudioDevices(): Promise<any>;
    changeOutputDevice(device_type: {
        device_type: any;
    }): Promise<any>;
}
