# agl-offline-voice-agent

FORTRAN:forcevariable = ",fortran"
RUNTIMETARGET:append:pn-gcc-runtime = " libquadmath"

# FIXME check if needed and/or submit upstream
BBCLASSEXTEND:pn-python3-charset-normalizer = "native nativesdk"

IMAGE_INSTALL:append = " python3-vosk-api vosk-kaldi vosk vosk-server python3-vosk-websocket-server python3-sounddevice ptest-runner"

PREFERRED_PROVIDER_virtual/vosk-model ?= "vosk-model-small-en-us"

DISTRO_FEATURES:append = " ptest"
EXTRA_IMAGE_FEATURES += "ptest-pkgs"
