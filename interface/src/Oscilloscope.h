//
//  Oscilloscope.h
//  interface
//
//  Created by Philip on 1/28/13.
//  Copyright (c) 2013 High Fidelity, Inc. All rights reserved.
//

#ifndef __interface__Oscilloscope__
#define __interface__Oscilloscope__

#include <cassert>

class Oscilloscope {
public:
    Oscilloscope(int width, int height, bool isEnabled);
    ~Oscilloscope();

    void addSamples(unsigned ch, short const* data, unsigned n);
    
    void render(int x, int y);

    static unsigned const MAX_CHANNELS = 3;
    static unsigned const MAX_SAMPLES_PER_CHANNEL = 4096; 

    volatile bool enabled;
    volatile bool inputPaused;

    void setLowpass(float w) { assert(w > 0.0f && w <= 1.0f); _lowpassFactor = w; }
    void setDownsampling(unsigned f) { assert(f > 0); _downsampleFactor = f; }
    
private:
    // don't copy/assign
    Oscilloscope(Oscilloscope const&); // = delete;
    Oscilloscope& operator=(Oscilloscope const&); // = delete;    

    // state variables

    unsigned        _width;
    unsigned        _height;
    short*          _samples;
    short*          _vertices;
    unsigned        _writePos[MAX_CHANNELS];

    float           _lowpassFactor;
    unsigned        _downsampleFactor;
};

#endif /* defined(__interface__oscilloscope__) */
