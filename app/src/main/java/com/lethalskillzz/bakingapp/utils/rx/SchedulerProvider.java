package com.lethalskillzz.bakingapp.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by ibrahimabdulkadir on 20/06/2017.
 */

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
