import io

file = io.open('src/enemies.txt', mode="w")

print("How many Simple Zombies do you want?")
simpleZombie = input()

print("How many Zombies with flags do you want?")
flagZombie = input()

print("How many Zombies with buckets on their heads do you want?")
bucketHead = input()

print("How many Zombies that jump do you want?")
paulVault = input()

print("How many Zombies with cons on their heads do you want?")
conHead = input()

file.write('SimpleZombie:{} FlagZombie:{} BucketheadZombie:{} PoleVaultingZombie:{} ConeheadZombie:{}'.format(simpleZombie, flagZombie, bucketHead, paulVault, conHead))

file.close()
