import { registerPlugin } from '@capacitor/core';
const CapacitorAudioOutputList = registerPlugin('CapacitorAudioOutputList', {
    web: () => import('./web').then(m => new m.CapacitorAudioOutputListWeb()),
});
export * from './definitions';
export { CapacitorAudioOutputList };
//# sourceMappingURL=index.js.map