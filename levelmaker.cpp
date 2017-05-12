#include <iostream>
#include <fstream>

std::ofstream g("src/enemies.txt");

int main(int argc, char const *argv[])
{	
	int simpleZombie, flagZombie, bucketHead, paulVault, conHead;
	std::cout << "How many Simple Zombies do you want?";
	std::cin >> simpleZombie;
	std::cout <<  "How many Zombies with flags do you want?";
	std::cin >> flagZombie;
	std::cout << "How many Zombies with buckets on their heads do you want?";
	std::cin >> bucketHead;
	std::cout << "How many Zombies that jump do you want?";
	std::cin >> paulVault;
	std::cout << "How many Zombies with cons on their heads do you want?";
	std::cin >> conHead;
	g << "SimpleZombie:" << simpleZombie << " FlagZombie:" << flagZombie << " BucketheadZombie:" << bucketHead << " PoleVaultingZombie:" << paulVault <<" ConeheadZombie:" << conHead;
	g.close();
	return 0;
}