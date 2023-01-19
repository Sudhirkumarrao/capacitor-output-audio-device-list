var capacitorCapacitorAudioOutputList = (function (exports, core) {
    'use strict';

    const CapacitorAudioOutputList = core.registerPlugin('CapacitorAudioOutputList', {
        web: () => Promise.resolve().then(function () { return web; }).then(m => new m.CapacitorAudioOutputListWeb()),
    });

    class CapacitorAudioOutputListWeb extends core.WebPlugin {
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

    var web = /*#__PURE__*/Object.freeze({
        __proto__: null,
        CapacitorAudioOutputListWeb: CapacitorAudioOutputListWeb
    });

    exports.CapacitorAudioOutputList = CapacitorAudioOutputList;

    Object.defineProperty(exports, '__esModule', { value: true });

    return exports;

})({}, capacitorExports);
//# sourceMappingURL=plugin.js.map
