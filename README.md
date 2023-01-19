# capacitor-output-audio-list-plugin

This capacitor plugin is used in android for listing all output audio devices connected to device

## Install

```bash
npm install capacitor-output-audio-list-plugin
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`getAudioDevices()`](#getaudiodevices)
* [`changeOutputDevice(...)`](#changeoutputdevice)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### getAudioDevices()

```typescript
getAudioDevices() => Promise<any>
```

**Returns:** <code>Promise&lt;any&gt;</code>

--------------------


### changeOutputDevice(...)

```typescript
changeOutputDevice(device_type: { device_type: any; }) => Promise<any>
```

| Param             | Type                               |
| ----------------- | ---------------------------------- |
| **`device_type`** | <code>{ device_type: any; }</code> |

**Returns:** <code>Promise&lt;any&gt;</code>

--------------------

</docgen-api>
