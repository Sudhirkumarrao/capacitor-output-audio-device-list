import { registerPlugin } from '@capacitor/core';

import type { CapacitorAudioOutputListPlugin } from './definitions';

const CapacitorAudioOutputList = registerPlugin<CapacitorAudioOutputListPlugin>('CapacitorAudioOutputList', {
  web: () => import('./web').then(m => new m.CapacitorAudioOutputListWeb()),
});

export * from './definitions';
export { CapacitorAudioOutputList };
