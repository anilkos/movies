You can scale the application in two way:

1) **Horizontal Scale**
We created a container app so you can deploy this image to cloud and can use kubernetes or swarm. If any scale needed , Kubernetes will replicate your docker containers and run new instances.That's why your application scale horizontal easily.
   (of course we need do define our deployment yml and scaling strategies)

If caching hurts us while scaling we can replace this with redis so that we have only one central cache.

2) **Vertical Scale**

A simple way to scale your software is to add on more hardware.(CPU,Ram,Hard Disk ...)
Faster processors and more memory will certainly grow your software, but you'll need space and hardware continually, which gets expensive quickly.
Also you we can optimize the code for improving the aplication performance.
