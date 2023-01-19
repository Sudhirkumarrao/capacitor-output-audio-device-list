export interface CapacitorAudioOutputListPlugin {
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
