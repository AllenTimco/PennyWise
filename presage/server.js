require('dotenv').config();

const {
    SmartSpectraSDK,
    breathingMetrics,
    cardioMetrics
} = require('@smartspectra/node-sdk');

const { decodeMetrics } = require('@smartspectra/node-sdk/messages');

const apiKey = process.env.PRESAGE_API_KEY;

if (!apiKey) {
    console.error('Missing PRESAGE_API_KEY in .env');
    process.exit(1);
}

const sdk = new SmartSpectraSDK({
    apiKey,
    requestedMetrics: [
        ...breathingMetrics,
        ...cardioMetrics
    ]
});

sdk.on('processingStatus', (status) => {
    console.log('Processing status:', status);
});

sdk.on('validationStatus', (code, timestampUs, hint) => {
    console.log('Validation:', code, hint);
});

sdk.on('metrics', (buf, timestampUs) => {
    const metrics = decodeMetrics(buf);

    console.log('Metrics:', metrics);
});

sdk.on('error', (code, message, retryable) => {
    console.error(
        'Presage error:',
        code,
        message,
        'retryable:',
        retryable
    );
});

sdk.useCamera();
sdk.start();

console.log('Presage is running.');
console.log('Look at the camera and breathe normally.');
console.log('Press Ctrl+C to stop.');

process.on('SIGINT', async () => {
    await sdk.stopAsync();
    await sdk.destroy();
    process.exit(0);
});