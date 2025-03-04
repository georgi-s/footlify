import React from 'react';
import {
    Box,
    Button,
    FormControl,
    FormLabel,
    Input,
    NumberInput,
    NumberInputField,
    Select,
    VStack,
    HStack,
    useToast,
} from '@chakra-ui/react';
import { Spieler, Torwart, Verteidiger, Mittelfeldspieler, Stuermer } from '../types';

type SpielerData = Spieler & Partial<Torwart & Verteidiger & Mittelfeldspieler & Stuermer>;

interface SpielerFormProps {
    spieler?: SpielerData;
    onSubmit: (spieler: Omit<SpielerData, 'id'>) => Promise<void>;
    onCancel: () => void;
}

const SpielerForm: React.FC<SpielerFormProps> = ({ spieler, onSubmit, onCancel }) => {
    const toast = useToast();
    const [formData, setFormData] = React.useState<Omit<SpielerData, 'id'>>({
        name: spieler?.name || '',
        position: spieler?.position || 'Torwart',
        alter: spieler?.alter || 18,
        trikotnummer: spieler?.trikotnummer || 1,
        gehalt: spieler?.gehalt || 0,
        // Position-specific defaults
        ...(spieler?.position === 'Torwart' ? {
            reaktion: (spieler as Torwart)?.reaktion || 50,
            fangsicherheit: (spieler as Torwart)?.fangsicherheit || 50,
        } : {}),
        ...(spieler?.position === 'Verteidiger' ? {
            zweikampfstaerke: (spieler as Verteidiger)?.zweikampfstaerke || 50,
            kopfballstaerke: (spieler as Verteidiger)?.kopfballstaerke || 50,
        } : {}),
        ...(spieler?.position === 'Mittelfeldspieler' ? {
            passgenauigkeit: (spieler as Mittelfeldspieler)?.passgenauigkeit || 50,
            ausdauer: (spieler as Mittelfeldspieler)?.ausdauer || 50,
        } : {}),
        ...(spieler?.position === 'Stuermer' ? {
            schusskraft: (spieler as Stuermer)?.schusskraft || 50,
            technik: (spieler as Stuermer)?.technik || 50,
        } : {}),
    });

    const handleSubmit = async (e: React.FormEvent) => {
        console.log('SpielerForm handleSubmit called with formData:', formData);
        e.preventDefault();
        try {
            await onSubmit(formData);
            toast({
                title: 'Erfolg',
                description: `Spieler wurde ${spieler ? 'aktualisiert' : 'erstellt'}.`,
                status: 'success',
                duration: 3000,
                isClosable: true,
            });
        } catch (error) {
            toast({
                title: 'Fehler',
                description: `Fehler beim ${spieler ? 'Aktualisieren' : 'Erstellen'} des Spielers.`,
                status: 'error',
                duration: 5000,
                isClosable: true,
            });
        }
    };

    const handleChange = (
        e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement> | number,
        field?: string
    ) => {
        console.log('SpielerForm handleChange called', { e, field });
        if (typeof e === 'number') {
            setFormData((prev) => {
                const newData = {
                    ...prev,
                    [field!]: e,
                };
                console.log('Updated formData (number):', newData);
                return newData;
            });
        } else {
            const { name, value } = e.target;
            setFormData((prev) => {
                const newData = {
                    ...prev,
                    [name]: value,
                };
                console.log('Updated formData (input):', newData);
                return newData;
            });
        }
    };

    return (
        <Box as="form" onSubmit={handleSubmit}>
            <VStack spacing={4} align="stretch" width="100%">
                <FormControl isRequired>
                    <FormLabel>Name</FormLabel>
                    <Input
                        name="name"
                        value={formData.name}
                        onChange={handleChange}
                        placeholder="Name des Spielers"
                    />
                </FormControl>

                <FormControl isRequired>
                    <FormLabel>Position</FormLabel>
                    <Select name="position" value={formData.position} onChange={handleChange}>
                        <option value="Torwart">Torwart</option>
                        <option value="Verteidiger">Verteidiger</option>
                        <option value="Mittelfeldspieler">Mittelfeldspieler</option>
                        <option value="Stuermer">Stürmer</option>
                    </Select>
                </FormControl>

                {formData.position === 'Torwart' && (
                    <>
                        <FormControl isRequired>
                            <FormLabel>Reaktion</FormLabel>
                            <NumberInput
                                min={1}
                                max={100}
                                value={formData.reaktion}
                                onChange={(_, value) => handleChange(value, 'reaktion')}
                            >
                                <NumberInputField />
                            </NumberInput>
                        </FormControl>
                        <FormControl isRequired>
                            <FormLabel>Fangsicherheit</FormLabel>
                            <NumberInput
                                min={1}
                                max={100}
                                value={formData.fangsicherheit}
                                onChange={(_, value) => handleChange(value, 'fangsicherheit')}
                            >
                                <NumberInputField />
                            </NumberInput>
                        </FormControl>
                    </>
                )}

                {formData.position === 'Verteidiger' && (
                    <>
                        <FormControl isRequired>
                            <FormLabel>Zweikampfstärke</FormLabel>
                            <NumberInput
                                min={1}
                                max={100}
                                value={formData.zweikampfstaerke}
                                onChange={(_, value) => handleChange(value, 'zweikampfstaerke')}
                            >
                                <NumberInputField />
                            </NumberInput>
                        </FormControl>
                        <FormControl isRequired>
                            <FormLabel>Kopfballstärke</FormLabel>
                            <NumberInput
                                min={1}
                                max={100}
                                value={formData.kopfballstaerke}
                                onChange={(_, value) => handleChange(value, 'kopfballstaerke')}
                            >
                                <NumberInputField />
                            </NumberInput>
                        </FormControl>
                    </>
                )}

                {formData.position === 'Mittelfeldspieler' && (
                    <>
                        <FormControl isRequired>
                            <FormLabel>Passgenauigkeit</FormLabel>
                            <NumberInput
                                min={1}
                                max={100}
                                value={formData.passgenauigkeit}
                                onChange={(_, value) => handleChange(value, 'passgenauigkeit')}
                            >
                                <NumberInputField />
                            </NumberInput>
                        </FormControl>
                        <FormControl isRequired>
                            <FormLabel>Ausdauer</FormLabel>
                            <NumberInput
                                min={1}
                                max={100}
                                value={formData.ausdauer}
                                onChange={(_, value) => handleChange(value, 'ausdauer')}
                            >
                                <NumberInputField />
                            </NumberInput>
                        </FormControl>
                    </>
                )}

                {formData.position === 'Stuermer' && (
                    <>
                        <FormControl isRequired>
                            <FormLabel>Schusskraft</FormLabel>
                            <NumberInput
                                min={1}
                                max={100}
                                value={formData.schusskraft}
                                onChange={(_, value) => handleChange(value, 'schusskraft')}
                            >
                                <NumberInputField />
                            </NumberInput>
                        </FormControl>
                        <FormControl isRequired>
                            <FormLabel>Technik</FormLabel>
                            <NumberInput
                                min={1}
                                max={100}
                                value={formData.technik}
                                onChange={(_, value) => handleChange(value, 'technik')}
                            >
                                <NumberInputField />
                            </NumberInput>
                        </FormControl>
                    </>
                )}

                <FormControl isRequired>
                    <FormLabel>Alter</FormLabel>
                    <NumberInput
                        min={16}
                        max={45}
                        value={formData.alter}
                        onChange={(_, value) => handleChange(value, 'alter')}
                    >
                        <NumberInputField />
                    </NumberInput>
                </FormControl>

                <FormControl isRequired>
                    <FormLabel>Trikotnummer</FormLabel>
                    <NumberInput
                        min={1}
                        max={99}
                        value={formData.trikotnummer}
                        onChange={(_, value) => handleChange(value, 'trikotnummer')}
                    >
                        <NumberInputField />
                    </NumberInput>
                </FormControl>

                <FormControl isRequired>
                    <FormLabel>Gehalt (€)</FormLabel>
                    <NumberInput
                        min={0}
                        step={1000}
                        value={formData.gehalt}
                        onChange={(_, value) => handleChange(value, 'gehalt')}
                    >
                        <NumberInputField />
                    </NumberInput>
                </FormControl>

                <HStack spacing={4} width="100%" justify="flex-end">
                    <Button onClick={onCancel} variant="outline">
                        Abbrechen
                    </Button>
                    <Button type="submit" colorScheme="blue">
                        {spieler ? 'Aktualisieren' : 'Erstellen'}
                    </Button>
                </HStack>
            </VStack>
        </Box>
    );
};

export default SpielerForm;
